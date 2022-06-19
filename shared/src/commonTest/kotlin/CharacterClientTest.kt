import com.example.marvelcharactermultiplatform.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CharacterClientTest {

@Test
fun queSeOrdenePrimeroElPersonajeConDescripcion() {
    val repository = KtorCharacterRepository()
    val client = CharacterClient(repository)
    val item1 = CharacterResult(11234314, "Item numero 1", "", Thumbnail("/path/to/file", ".img"))
    val item2 = CharacterResult(11234314, "Item numero 2", "Item con descripción", Thumbnail("/path/to/file", ".img"))

    val list = listOf(item1, item2)

    val listResult = client.sort(list)
    assertEquals(item2.description, listResult[0].description)
}
}