package com.github.olegosipenko.detektrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtLambdaExpression
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

    checkIfPresent(whenExpressions, function)
  }

  override fun visitLambdaExpression(lambdaExpression: KtLambdaExpression) {
    super.visitLambdaExpression(lambdaExpression)

    val whenExpressions = lambdaExpression.bodyExpression?.statements
      ?.filterIsInstance<KtWhenExpression>()

    checkIfPresent(whenExpressions, lambdaExpression)
  }

  private fun checkIfPresent(
    whenExpressions: List<KtWhenExpression>?, psiElement: PsiElement
  ) {
    if (whenExpressions?.isNotEmpty() == true) {
      report(
        CodeSmell(
          issue, Entity.from(psiElement), MESSAGE
        )
      )
    }
  }
}

internal const val DESCR = "When should be used as expression"
internal const val MESSAGE = "When not used as expression"
