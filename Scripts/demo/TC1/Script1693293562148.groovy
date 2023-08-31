import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.setViewPortSize(720, 720)
WebUI.navigateToUrl('http://demoaut.katalon.com/')
WebUI.delay(3)
WebUI.closeBrowser()
