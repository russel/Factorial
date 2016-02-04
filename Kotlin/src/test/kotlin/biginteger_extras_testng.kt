package uk.org.winder.maths.factorial

import org.testng.annotations.Test

import org.testng.Assert.assertEquals
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue

class BigInteger_Extras_TestNG {

  @Test
  fun value_is_in_a_range_it_is_supposed_to_be() {
    assertTrue(5.bigint in 0.bigint rangeTo 10.bigint)
  }

  @Test
  fun value_is_not_in_a_range_it_is_not_supposed_to_be_in() {
    assertFalse(-5.bigint in 0.bigint rangeTo 10.bigint)
  }

  @Test
  fun upward_range_generates_the_expected_values() {
    assertEquals((0.bigint rangeTo 5.bigint).toList(), listOf(0, 1, 2, 3, 4, 5).map{it.bigint})
  }

  @Test
  fun upward_stridded_range_generates_the_expected_values() {
    assertEquals((0.bigint rangeTo 5.bigint step 2).toList(), listOf(0, 2, 4).map{it.bigint})
  }

  @Test
  fun downward_range_generates_the_expected_values() {
    assertEquals((5.bigint downTo 0.bigint).toList(), listOf(5, 4, 3, 2, 1, 0).map{it.bigint})
  }

  @Test
  fun downward_strided_range_generates_the_expected_values() {
    assertEquals((5.bigint downTo 0.bigint step 2).toList(), listOf(5, 3, 1).map{it.bigint})
  }

}
