import com.example.marvelcharactermultiplatform.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CharacterClientTest {

    @Test
    fun queSeOrdenePrimeroElPersonajeConDescripcion() {
        val repository = KtorCharacterRepository()
        val client = CharacterClient(repository)
        val item1 = CharacterResult(11234314, "Item numero 1", "", Thumbnail("/path/to/file", ".img"))
        val item2 = CharacterResult(
            11234314,
            "Item numero 2",
            "Item con descripción",
            Thumbnail("/path/to/file", ".img")
        )

        val list = listOf(item1, item2)

        val listResult = client.sort(list)
        assertEquals(item2.description, listResult[0].description)
    }

    @Test
    fun queGenereElMD5Correctamente() {
        val texto = "Cualquier cosa"
        val MD5Correcto = "3effb29f739f4d54c8f25b5fe9dceded"

        val MD5Generado = CharacterClient.getMd5(texto)
        assertEquals(MD5Correcto, MD5Generado)
    }

    @Test
    fun queSeOrdeneConIDDescendenteSinDescripcion() {
        val repository = KtorCharacterRepository()
        val client = CharacterClient(repository)
        val item1 = CharacterResult(11234314, "Item numero 1", "", Thumbnail("/path/to/file", ".img"))
        val item2 = CharacterResult(11234500, "Item numero 1", "", Thumbnail("/path/to/file", ".img"))

        val list = listOf(item1, item2)
        val listResult = client.sort(list)

        assertEquals(item2.id, listResult[0].id)
        assertEquals(item1.id, listResult[1].id)
    }

    @Test
    fun queSeOrdeneConIDAscendenteConDescripcion() {
        val repository = KtorCharacterRepository()
        val client = CharacterClient(repository)
        val item1 = CharacterResult(11234500, "Item numero 1", "Descripcion1", Thumbnail("/path/to/file", ".img"))
        val item2 = CharacterResult(11234314, "Item numero 1", "Descripcion2", Thumbnail("/path/to/file", ".img"))

        val list = listOf(item1, item2)
        val listResult = client.sort(list)

        assertEquals(item2.id, listResult[0].id)
        assertEquals(item1.id, listResult[1].id)
    }
}