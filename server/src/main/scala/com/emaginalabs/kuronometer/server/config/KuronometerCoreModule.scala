package com.emaginalabs.kuronometer.server.config

import com.emaginalabs.kuronometer.core.config.KuronometerConfigProviderComponent
import com.emaginalabs.kuronometer.core.service.{BuildExecutionReportCreator, BuildExecutionReportCreatorComponent}
import com.emaginalabs.kuronometer.core.storage.{BuildExecutionReportRepositoryComponent, BuildExecutionStatsRepository, BuildExecutionStatsRepositoryComponent, WSClientComponent}
import com.google.inject.Provides
import com.twitter.inject.TwitterModule

object KuronometerCoreModule
  extends TwitterModule
    with BuildExecutionReportCreatorComponent.Default
    with BuildExecutionReportRepositoryComponent.Default
    with BuildExecutionStatsRepositoryComponent.Default
    with KuronometerConfigProviderComponent.Default
    with WSClientComponent.Default {

  @Provides
  def providesReportCreator: BuildExecutionReportCreator = reportCreator

  @Provides
  def providesBuildExecutionStatsRepository: BuildExecutionStatsRepository = statsRepository
}
