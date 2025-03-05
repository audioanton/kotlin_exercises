package advent_of_code_2022_day_7

import advent_of_code_2022_day_7_folders.Folder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class FolderTest {

@Test
 fun changeFolder() {
     val folder = Folder("/")
     val b = folder.changeFolder("B")
     assertEquals(1, folder.subFolders.size)
     assertEquals("B", b.folderName)
 }
}