param location string = 'westus2'
param planName string = 'asp-spring-boot'
param webAppNameProd string = 'springboot-sanjay0303'
param webAppNameDev string = 'springboot-sanjay0303-dev'
param appInsightsName string = 'appi-springboot-sanjay0303'
param logAnalyticsName string = 'law-springboot-sanjay0303'

resource logAnalytics 'Microsoft.OperationalInsights/workspaces@2022-10-01' = {
  name: logAnalyticsName
  location: location
  properties: {
    sku: {
      name: 'PerGB2018'
    }
    retentionInDays: 30
  }
}

resource appInsights 'Microsoft.Insights/components@2020-02-02' = {
  name: appInsightsName
  location: location
  kind: 'web'
  properties: {
    Application_Type: 'web'
    WorkspaceResourceId: logAnalytics.id
  }
}

resource plan 'Microsoft.Web/serverfarms@2022-09-01' = {
  name: planName
  location: location
  kind: 'linux'
  sku: {
    name: 'F1'
    tier: 'Free'
  }
  properties: {
    reserved: true
  }
}

resource webAppProd 'Microsoft.Web/sites@2022-09-01' = {
  name: webAppNameProd
  location: location
  properties: {
    serverFarmId: plan.id
    httpsOnly: true
    siteConfig: {
      linuxFxVersion: 'JAVA|21-java21'
      alwaysOn: false
    }
  }
}

resource webAppDev 'Microsoft.Web/sites@2022-09-01' = {
  name: webAppNameDev
  location: location
  properties: {
    serverFarmId: plan.id
    httpsOnly: true
    siteConfig: {
      linuxFxVersion: 'JAVA|21-java21'
      alwaysOn: false
    }
  }
}

output appInsightsConnectionString string = appInsights.properties.ConnectionString
output webAppProdName string = webAppProd.name
output webAppDevName string = webAppDev.name
