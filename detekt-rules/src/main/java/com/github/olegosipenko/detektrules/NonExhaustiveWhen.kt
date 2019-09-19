package com.github.olegosipenko.detektrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtWhenExpression

class NonExhaustiveWhen(config: Config = Config.empty): Rule(config) {
  override val issue = Issue(
    javaClass.simpleName,
    Severity.Defect,
    DESCR,
    Debt.FIVE_MINS
  )

  override fun visitNamedFunction(function: KtNamedFunction) {
    super.visitNamedFunction(function)

    val whenExpressions =
      function.children.filterIsInstance<KtBlockExpression>()
        .flatMap { blockExpression -> blockExpression.children.asIterable() }
        .filterIsInstance<KtWhenExpression>()
    if (whenExpressions.isNotEmpty()) {
      report(
        CodeSmell(
          issue, Entity.from(function), MESSAGE
        )
      )
    }
  }
}

internal const val DESCR = "When should be used as expression"
internal const val MESSAGE = "When not used as expression"
