#!/usr/bin/groovy
def call(goal) {
    if (goal == 'checkstyle') {
        options = [
            '-Dcheckstyle.failOnViolation=true', 
            '-Dcheckstyle.console=true', 
            '-Dcheckstyle.violationSeverity=warning',
            '-Dcheckstyle.config.location=google_checks.xml',
        ]
        goal = 'checkstyle:check'
    }
    else if (goal == 'cobertura') {
        options = [
            '-Dcobertura.report.format=xml'
        ]
        goal = 'cobertura:cobertura'
    }
    l_cmd = ['mvn ', options.join(' '), goal]
    cmd = l_cmd.join(' ')
    sh(script: cmd)
}