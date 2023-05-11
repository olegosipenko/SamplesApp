package com.github.olegosipenko.detektrules

import io.gitlab.arturbosch.detekt.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class NonExhaustiveWhenTest {
  @Test
  @DisplayName("non compliant when statement should warn")
  internal fun nonCompliantCodeShouldWarn() {
    val findings = NonExhaustiveWhen().lint(WHEN_STATEMENT.trimIndent())

    assertThat(findings).hasSize(1)
    assertThat(findings[0].message).isEqualTo(MESSAGE)
  }

  @DisplayName("compliant ")
  @MethodSource("compliantProvider")
  @ParameterizedTest(name = "{1} should not warn")
  internal fun testCompliantWhen(source: String, whenKind: String) {
    val findings = NonExhaustiveWhen().lint(source)
    assertThat(findings).isEmpty()
  }

  companion object {
    @JvmStatic
    fun compliantProvider(): Stream<Arguments> =
      Stream.of(
        arguments(COMPLIANT_WHEN_DOT, "dot expression"),
        arguments(COMPLIANT_WHEN_PROPERTY, "property"),
        arguments(COMPLIANT_WHEN_RETURN, "return")
      )
  }
}

const val WHEN_STATEMENT = """
        sealed class S {
          object A: S()
          object B: S()
        }
        
        class WhenTester {
          fun checkS(state: S) {
            when (state) {
              S.A -> println("a")
            }
          }
        }
        """

const val COMPLIANT_WHEN_DOT = """
        sealed class S {
          object A: S()
          object B: S()
        }
        
        class WhenTester {
          fun checkS(state: S) {
            when (state) {
              S.A -> println("a")
              S.B -> println("b")
            }.exhaustive
          }
        }
        """

const val COMPLIANT_WHEN_PROPERTY = """
        sealed class S {
          object A: S()
          object B: S()
        }
        
        class WhenTester {
          fun checkS(state: S) {
            val r = when (state) {
              S.A -> println("a")
              S.B -> println("b")
            }
          }
        }
        
        """

const val COMPLIANT_WHEN_RETURN = """
        sealed class S {
          object A: S()
          object B: S()
        }
        
        class WhenTester {
          fun checkS(state: S) {
            return when (state) {
              S.A -> println("a")
              S.B -> println("b")
            }
          }
        }
  
"""
