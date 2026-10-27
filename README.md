# Examen — Programación Móvil (Kotlin Multiplatform)

## Integrantes
- Bernardo Rios Tapia
- Mateo Quintanilla Calvimonte
- Rafael Soliz Contreras

Rama: `feature/examen-Rios-Quintanilla-Soliz`

La app tiene dos pestañas, una por cada ejercicio:

| Pestaña | Ejercicio | Servicios |
|---|---|---|
| **E50-1 Sync** | Remote Config → Room → Sincronización en background | Remote Config + Room + WorkManager |
| **E50-2 Tareas** | Lista colaborativa con contador | Firestore + Realtime Database |

---

## Justificación de la combinación elegida

### E50-1 · Remote Config + Room + WorkManager
- **Remote Config** sirve para cambiar contenido de la app (features y banners) desde la consola,
  sin publicar una nueva versión.
- **Room** guarda una copia local. Así la app siempre tiene algo que mostrar, incluso sin internet,
  y la pantalla lee **solo de Room**: una sola fuente de datos, más simple y predecible.
- **WorkManager** descarga los datos en segundo plano. Si no hay internet, espera a que vuelva y lo
  hace solo; si falla, lo reintenta. La pantalla no tiene que esperar a la red.

### E50-2 · Firestore + Realtime Database
- **Firestore** guarda las tareas: son documentos con varios campos (título, completada) y se pueden
  consultar y escuchar en tiempo real.
- **Realtime Database** guarda solo el contador de completadas: un número que cambia seguido y que
  todos ven al instante. Es más liviana y rápida para un valor así.
- Como las dos bases no comparten transacción, se usa una **transacción en cada una** y un
  **rollback lógico**: si falla el contador, se deshace el cambio de la tarea para que las dos
  bases sigan diciendo lo mismo.

---

## Cómo funciona (en palabras simples)

### E50-1
1. Al abrir la app, un **Worker** (tarea en segundo plano) pide a Remote Config el parámetro
   `app_config` (un JSON con features y banners).
2. El Worker guarda esos datos en **Room** (tablas `features` y `banners`).
3. La pantalla muestra lo que hay en Room y se actualiza sola cuando Room cambia.
4. **Sin internet**: la app abre con lo último guardado y muestra el aviso **"Datos cacheados"**.
   Cuando vuelve el internet, el Worker sincroniza solo y el aviso desaparece.
5. **Deslizar hacia abajo** (pull-to-refresh) lanza el Worker a mano.

### E50-2
1. La lista de tareas viene de Firestore (colección `tareas`) y se actualiza en vivo.
2. El contador viene de Realtime Database (`/stats/completadas`) y se actualiza en vivo, con animación.
3. Al marcar o desmarcar una tarea:
   - **Paso 1:** se cambia la tarea en Firestore (con transacción: si ya tenía ese valor, no se cuenta dos veces).
   - **Paso 2:** se suma o resta 1 al contador en Realtime Database (con transacción).
   - **Si el paso 2 falla:** se deshace el paso 1, se escribe en el log y se muestra el error en pantalla.

---

## Instrucciones para probar

### Antes de empezar (Firebase Console)
1. **Remote Config** → agregar el parámetro `app_config` (tipo JSON) con este valor y **publicar**:
   ```json
   {"features":[{"key":"f1","name":"Feature 1","enabled":true},{"key":"f2","name":"Feature 2","enabled":false}],"banners":[{"id":"b1","title":"Banner 1","message":"Primer banner","active":true}]}
   ```
2. **Firestore** → debe existir la base (las tareas se pueden crear desde la app).
3. **Realtime Database → Reglas** → permitir leer y escribir (solo para pruebas):
   ```json
   { "rules": { ".read": true, ".write": true } }
   ```

### Correr la app
- Abrir el proyecto en Android Studio y darle **Run** a `androidApp`,
  o por consola: `./gradlew :androidApp:assembleDebug`

### Probar E50-1
1. Abrir la app → pestaña **E50-1 Sync** → aparecen los banners y las features.
2. Cambiar algo en Remote Config y publicar → en la app, **deslizar hacia abajo** → se ve el cambio.
3. Activar **modo avión** → cerrar la app por completo → abrirla: siguen los datos y sale **"Datos cacheados"**.
4. Quitar el modo avión → en unos segundos el aviso desaparece solo.
5. En Logcat (filtro `ConfigSyncWorker`) se ve `ConfigSyncWorker: sincronizado`.

### Probar E50-2
1. Pestaña **E50-2 Tareas** → escribir una tarea → **Agregar**: aparece en la lista y en Firestore.
2. Marcarla: el contador sube; desmarcarla: baja. En la consola, `/stats/completadas` cambia igual.
3. Editar una tarea desde la consola de Firestore (por ejemplo el título): la lista se actualiza sola.
4. **Probar el error:** poner en las reglas de Realtime Database `".write": false` y marcar una tarea.
   Sale un mensaje en rojo, la tarea vuelve a como estaba y en Logcat (filtro `Tasks:`) se ve el rollback.

> Las tareas deben marcarse desde la app. Si se marcan a mano en la consola de Firestore,
> el contador no se entera.

---

## Dónde está el código
Todo está en `shared/src/commonMain/kotlin/org/ucb/appp1/` siguiendo Clean Architecture (data / domain / presentation):

| Carpeta | Contenido |
|---|---|
| `sync/` | E50-1: Remote Config, entidades y DAO de Room, repositorio, ViewModel y pantalla |
| `tasks/` | E50-2: Firestore, Realtime Database, repositorio con rollback, ViewModel y pantalla |
| `shared/src/androidMain/.../sync/` | Worker y programador de WorkManager (solo Android) |

> WorkManager es solo de Android, así que el ejercicio E50-1 corre en Android.
