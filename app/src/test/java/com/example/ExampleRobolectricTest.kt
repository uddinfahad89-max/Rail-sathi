package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.AppLanguage
import com.example.data.model.SplitType
import com.example.data.repository.RailwayRepository
import com.example.ui.util.AppStrings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("RailSathi", appName)
    }

    @Test
    fun `test split ticket calculation generates same train seat switch`() {
        val repository = RailwayRepository()
        val splits = repository.calculateSplitTickets("HWH", "NDLS", "28 Sep 2026")
        assertTrue("Should return split ticket options", splits.isNotEmpty())

        val sameTrainSplit = splits.find { it.splitType == SplitType.SAME_TRAIN_SEAT_SWITCH }
        assertNotNull("Should contain same train seat switch option", sameTrainSplit)
        assertEquals(100, sameTrainSplit!!.overallConfirmationPercent)
    }

    @Test
    fun `test PNR status prediction calculation`() {
        val repository = RailwayRepository()
        val pnr = repository.checkPnrStatus("6428190342")
        assertEquals("6428190342", pnr.pnrNumber)
        assertTrue("Confirmation probability should be high", pnr.confirmationProbabilityPercent >= 90)
    }

    @Test
    fun `test multilingual localization strings`() {
        assertEquals("ট্রেন ও স্প্লিট", AppStrings.tabSearch(AppLanguage.BENGALI))
        assertEquals("ट्रेन व स्प्लिट", AppStrings.tabSearch(AppLanguage.HINDI))
        assertEquals("Trains & Split", AppStrings.tabSearch(AppLanguage.ENGLISH))

        val repo = RailwayRepository()
        val hwh = repo.getStationByCode("HWH")
        assertEquals("হাওড়া জংশন", hwh.getName(AppLanguage.BENGALI))
        assertEquals("हावड़ा जंक्शन", hwh.getName(AppLanguage.HINDI))
        assertEquals("Howrah Junction", hwh.getName(AppLanguage.ENGLISH))
    }
}
