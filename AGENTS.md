# AGENTS.md — ucbp1_project

## Proyecto
Kotlin Multiplatform (KMP) + Jetpack Compose Multiplatform
Autor: BerniUCB (bernardo.rios@ucb.edu.bo)
Repo docente de referencia: https://github.com/calyr/ucbp1_project (rama feature/room)

---

## Estructura de ramas
- `main` — rama principal
- `feature/ROOM` — configuración de Room 3 KMP
- `feature/databaserealtime` — Firebase Realtime Database + módulo exchange + Dollar (rama actual)
- `feature/crossref` — consumo API Crossref
- `feature/userinformation` — info de usuario GitHub

---

## Room 3 — Configuración (ya hecha)

### libs.versions.toml
```toml
[versions]
room3 = "3.1.0-alpha01"
sqlite = "2.7.1"

[libraries]
androidx-sqlite-bundled = { module = "androidx.sqlite:sqlite-bundled", version.ref = "sqlite" }
androidx-room3-runtime = { module = "androidx.room3:room3-runtime", version.ref = "room3" }
androidx-room3-compiler = { module = "androidx.room3:room3-compiler", version.ref = "room3" }
androidx-room3-sqlite-wrapper = { module = "androidx.room3:room3-sqlite-wrapper", version.ref = "room3" }
```

### shared/build.gradle.kts
- Plugin: `alias(libs.plugins.ksp)`
- `commonMain`: `room3-runtime` + `sqlite-bundled`
- `androidMain`: `room3-sqlite-wrapper`
- `dependencies`: `add("kspAndroid", libs.androidx.room3.compiler)`
- Plugin `androidx.room3` NO agregar — no está publicado, causa "Unresolved reference 'room'"
- Bloque `room { schemaDirectory(...) }` NO agregar — requiere el plugin que no existe

---

## Firebase Realtime Database — Configuración (ya hecha en feature/databaserealtime)

### libs.versions.toml — agregar
```toml
[versions]
firebaseDatabase = "2.7.0"
googleServices = "4.4.2"

[libraries]
firebase-database = { module = "dev.gitlive:firebase-database", version.ref = "firebaseDatabase" }

[plugins]
googleServices = { id = "com.google.gms.google-services", version.ref = "googleServices" }
```

### shared/build.gradle.kts
- Firebase va como string literal en commonMain (el accessor `libs.firebase.database` no se genera correctamente):
  ```kotlin
  implementation("dev.gitlive:firebase-database:2.7.0")
  ```
- JVM target debe ser **17** (no 11) — firebase-database requiere JVM 17

### androidApp/build.gradle.kts
- Agregar plugin: `alias(libs.plugins.googleServices)`
- Cambiar `JvmTarget.JVM_11` → `JvmTarget.JVM_17`
- Cambiar `JavaVersion.VERSION_11` → `JavaVersion.VERSION_17`

### google-services.json
- Va en `androidApp/google-services.json` — NO modificar, solo asegurarse que existe

---

## Patrón para agregar una nueva tabla a Room

### 1. Crear Entity en:
`shared/src/commonMain/kotlin/org/ucb/appp1/<modulo>/data/entity/<Nombre>Entity.kt`
```kotlin
@Entity(tableName = "nombre_tabla")
data class NombreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    // ... columnas
)
```

### 2. Crear DAO en:
`shared/src/commonMain/kotlin/org/ucb/appp1/<modulo>/data/dao/<Nombre>Dao.kt`
```kotlin
@Dao
interface NombreDao {
    @Query("SELECT * FROM nombre_tabla")
    suspend fun getList(): List<NombreEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: NombreEntity)
    // ...
}
```

### 3. Registrar en Database.kt:
`shared/src/commonMain/kotlin/org/ucb/appp1/config/Database.kt`
```kotlin
@Database(entities = [DollarEntity::class, NombreEntity::class], version = 2)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDollarDao(): DollarDao
    abstract fun getNombreDao(): NombreDao
}
```
> Cada entity tiene su propio DAO. Son independientes entre sí.
> Solo hay que crear la entity + dao y registrarlos en AppDatabase.
> La inyección de dependencias ya está configurada en PlatformModule.

---

## Archivos de Room ya creados
| Archivo | Descripción |
|---|---|
| `config/Database.kt` | AppDatabase + AppDatabaseConstructor (commonMain) |
| `config/Database.android.kt` | getDatabaseBuilder con Context (androidMain) |
| `di/PlatformModule.kt` | expect fun platformModule() (commonMain) |
| `di/PlatformModule.android.kt` | actual con AppDatabase en Koin (androidMain) |
| `di/PlatformModule.ios.kt` | actual vacío (iosMain) |
| `movies/data/dao/DollarDao.kt` | DAO de la tabla dollars |
| `movies/data/entity/DollarEntity.kt` | Entity de la tabla dollars |

## Archivos de Firebase Realtime Database ya creados (feature/databaserealtime)
| Archivo | Descripción |
|---|---|
| `exchange/data/datasource/RealTimeDataBase.kt` | Conecta a Firebase, observa nodo `app_dollar` como Flow |
| `exchange/data/repository/ExchangeRepositoryImpl.kt` | Implementación del repositorio |
| `exchange/domain/repository/ExchangeRepository.kt` | Interfaz del repositorio |
| `exchange/domain/usecase/ObserveExchangeUseCase.kt` | Use case que retorna Flow<String?> |
| `exchange/presentation/viewmodel/ExchangeViewModel.kt` | Colecta el Flow, expone StateFlow |
| `exchange/presentation/screen/ExchangeScreen.kt` | Pantalla principal (muestra valor de Firebase) |
| `movies/domain/model/DollarModel.kt` | Modelo de dominio Dollar |
| `movies/domain/repository/DollarRepository.kt` | Interfaz repositorio Dollar |
| `movies/data/datasource/DollarLocalDataSource.kt` | DataSource local con Room |
| `movies/data/repository/DollarRepositoryImpl.kt` | Implementación repositorio Dollar |
| `movies/presentation/viewmodel/DollarViewModel.kt` | ViewModel con events/state/effects |
| `movies/presentation/screen/DollarScreen.kt` | Pantalla con botón Add + contador |

---

## Errores conocidos y soluciones

| Error | Causa | Solución |
|---|---|---|
| `Unresolved reference 'room'` | Plugin `androidx.room3` no publicado | No agregar ese plugin ni el bloque `room {}` |
| `Unresolved reference 'firebase'` | Accessor `libs.firebase.database` no se genera | Usar string literal: `"dev.gitlive:firebase-database:2.7.0"` |
| `Cannot inline bytecode JVM 17 into JVM 11` | firebase-database requiere JVM 17 | Cambiar jvmTarget a JVM_17 en shared y androidApp |

---

## Reglas generales
- Solo commits con autor BerniUCB — nunca agregar co-autores
- Siempre crear ramas con prefijo `feature/`
- No adelantarse: solo crear lo que se pide o lo que genera error
- Seguir estructura del repo del docente: https://github.com/calyr/ucbp1_project
