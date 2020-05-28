import eu.indigo.compose.parser.ConfigParser
import eu.indigo.compose.ProjectConfiguration
import eu.indigo.compose.parser.ConfigValidation
import eu.indigo.compose.ComposeFactory
import eu.indigo.compose.DockerCompose

def call(String configFile='./.sqa/config.yml') {
    def yamlContent = readFile file: yamlFile
    def yaml = readYaml file: configFile
    def schema = libraryResource('eu/indigo/compose/parser/schema.json')
    def buildNumber = Integer.parseInt(env.BUILD_ID)
    def nodeAgent = null
    ProjectConfiguration projectConfig = null

    // validate config.yml
    validator = new ConfigValidation()
    validator.validate(yamlContent, schema)
    projectConfig = ConfigParser.parse(yaml, env, this.nodeAgent)
    try {
        projectConfig.nodeAgent = ComposeFactory.setFactory(this.getClass().classLoader.loadClass(this.nodeAgent?, true, false)?.newInstance(this))
    } catch (ClassNotFoundException | CompilationFailedException e) {
        error 'BuildStages: Node agent not defined'
    }
    return projectConfig

}