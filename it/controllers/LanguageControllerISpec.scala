package controllers

import itutil.IntegrationSpecBase
import play.api.Play
import play.api.http.Status._
import play.api.i18n.MessagesApi
import play.api.test.FakeApplication

class LanguageControllerISpec extends IntegrationSpecBase {

  override implicit lazy val app: FakeApplication = FakeApplication(additionalConfiguration = fakeConfig())
  implicit lazy val messagesAPI: MessagesApi = app.injector.instanceOf[MessagesApi]

  s"${controllers.routes.LanguageController.switchToLanguage("english")}" must {
    "swap to english and redirect back" in {
      val response = await(buildClientLanguage("english", "testRef").get)
      response.status shouldBe SEE_OTHER
      response.cookie(Play.langCookieName).get.value shouldBe Some("en")
    }
  }

  s"${controllers.routes.LanguageController.switchToLanguage("cymraeg")}" must {
    "swap to welsh and redirect back" in {
      val response = await(buildClientLanguage("cymraeg", "testRef").get)
      response.status shouldBe SEE_OTHER
      response.cookie(Play.langCookieName).get.value shouldBe Some("cy")
    }
  }

}