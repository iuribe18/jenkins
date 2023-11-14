job ('DSL_Example') {
  	description('DSL Job description')
  scm {
    git ('https://github.com/iuribe18/jenkins.git', 'main') { node ->
      node / gitConfigName('iuribe18')
      node / gitConfigEmail('ivanuribegonzalez@gmail.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Ivancho', description = 'Nombre del Usuario')
    choiceParam('planeta', ['Tierra (default)', 'Mercurio', 'Urano', 'Saturno', 'Plutón', 'Neptuno', 'Venus'])
    booleanParam('agent', false)
    }
   triggers {
     cron('H/7 * * * *')
    }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('ivanuribegonzalez@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
  	}
  }
}
