package com.github.olegosipenko.detektrules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRulesetProvider: RuleSetProvider {
  override val ruleSetId: String = "detekt-rules"

  override fun instance(config: Config): RuleSet = RuleSet(
    ruleSetId,
    listOf(
      NonExhaustiveWhen(config)
    )
  )
}
