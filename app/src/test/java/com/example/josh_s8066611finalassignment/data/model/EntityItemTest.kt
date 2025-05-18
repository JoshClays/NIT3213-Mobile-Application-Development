package com.example.josh_s8066611finalassignment.data.model

import org.junit.Test
import org.junit.Assert.*

class EntityItemTest {

    @Test
    fun `create EntityItem with valid data`() {
        val entity = EntityItem(
            title = "Test Book",
            author = "Test Author",
            genre = "Fiction",
            publicationYear = 2024,
            description = "Test Description"
        )

        assertEquals("Test Book", entity.title)
        assertEquals("Test Author", entity.author)
        assertEquals("Fiction", entity.genre)
        assertEquals(2024, entity.publicationYear)
        assertEquals("Test Description", entity.description)
    }

    @Test
    fun `EntityItem equals works correctly`() {
        val entity1 = EntityItem(
            title = "Test Book",
            author = "Test Author",
            genre = "Fiction",
            publicationYear = 2024,
            description = "Test Description"
        )

        val entity2 = EntityItem(
            title = "Test Book",
            author = "Test Author",
            genre = "Fiction",
            publicationYear = 2024,
            description = "Test Description"
        )

        assertEquals(entity1, entity2)
    }

    @Test
    fun `EntityItem with different values are not equal`() {
        val entity1 = EntityItem(
            title = "Test Book 1",
            author = "Test Author",
            genre = "Fiction",
            publicationYear = 2024,
            description = "Test Description"
        )

        val entity2 = EntityItem(
            title = "Test Book 2",
            author = "Test Author",
            genre = "Fiction",
            publicationYear = 2024,
            description = "Test Description"
        )

        assertNotEquals(entity1, entity2)
    }
} 