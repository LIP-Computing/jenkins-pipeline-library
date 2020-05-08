#!/usr/bin/groovy

import static eu.indigo.DockerCompose.composeToxRun

/**
 * Run the pipeline within Docker Compose
 * This call defines a step that returns a stage
 *
 * Parameters are already defined in function definition
 */
def call(String service, String command, String compose_file='', String workdir='') {
    composeToxRun(service, command, compose_file, workdir)
}
