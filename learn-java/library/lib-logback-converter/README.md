https://logback.qos.ch/manual/onJoran.html#newRule

ConversionRuleModel
ConversionRuleAction
ConversionRuleModelHandler

https://logback.qos.ch/beagle/index.html

org.springframework.boot.logging.logback.SpringBootJoranConfigurator

@Override
public void addElementSelectorAndActionAssociations(RuleStore ruleStore) {
    super.addElementSelectorAndActionAssociations(ruleStore);
    ruleStore.addRule(new ElementSelector("configuration/springProperty"), SpringPropertyAction::new);
    ruleStore.addRule(new ElementSelector("*/springProfile"), SpringProfileAction::new);
    ruleStore.addTransparentPathPart("springProfile");
}

ch.qos.logback.core.joran.JoranConfiguratorBase
ch.qos.logback.classic.joran.JoranConfigurator
org.springframework.boot.logging.logback.SpringBootJoranConfigurator

ch.qos.logback.classic.joran.JoranConfigurator
