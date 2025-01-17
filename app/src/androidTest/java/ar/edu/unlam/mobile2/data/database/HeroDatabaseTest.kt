package ar.edu.unlam.mobile2.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import ar.edu.unlam.mobile2.data.database.dao.HeroDao
import ar.edu.unlam.mobile2.data.database.entities.AppearanceEntity
import ar.edu.unlam.mobile2.data.database.entities.BiographyEntity
import ar.edu.unlam.mobile2.data.database.entities.HeroEntity
import ar.edu.unlam.mobile2.domain.hero.Connections
import ar.edu.unlam.mobile2.domain.hero.Image
import ar.edu.unlam.mobile2.domain.hero.Powerstats
import ar.edu.unlam.mobile2.domain.hero.Work
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class HeroDatabaseTest {

    /*
        Esta regla es para que se ejecute cada línea de código de forma secuencial.
        Nos sirve en casos en donde se necesite el uso de livedata, flow, suspend functions, etc.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataBase: HeroDataBase
    private lateinit var dao: HeroDao

    @Before
    fun setUp() {
        dataBase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = HeroDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = dataBase.getHeroDao()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    /*
        runTest es una corutina para testing
     */
    @Test
    fun insertHeroEntity() = runTest {
        val heroEntity = HeroEntity(
            id = 1,
            powerstats = Powerstats(),
            isFavorite = false,
            connections = Connections(),
            appearance = AppearanceEntity(),
            biography = BiographyEntity(),
            image = Image(),
            name = "Mr. Test",
            work = Work()
        )

        dao.insertHero(hero = heroEntity)

        val allHeroes = dao.getAll()

        assertThat(allHeroes).contains(heroEntity)
        assertThat(allHeroes).containsExactly(heroEntity)
        assertThat(allHeroes).hasSize(1)
        assertThat(allHeroes).isNotEmpty()
    }

}