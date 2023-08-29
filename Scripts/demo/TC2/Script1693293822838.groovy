import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

TestObject makeTestObject(String id, String selector) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("css", ConditionType.EQUALS, selector)
	return tObj	
}
TestObject tObj = makeTestObject("lorum", "#ipsum")

WebUI.openBrowser('')
WebUI.setViewPortSize(800, 800)
WebUI.navigateToUrl('https://duckduckgo.com/')
WebUI.delay(3)
WebUI.verifyElementPresent(tObj, 10)
WebUI.closeBrowser()
