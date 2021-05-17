import org.scalamock.scalatest.MockFactory
import org.scalatest.Suite
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

// Назваем тестируемый класс по схеме *имя сервиса* + Spec
class FormattingHelperServiceSpec extends AnyFlatSpec with MockFactory with Matchers with Suite {

  // Этот тест написан не верно. Мы хотим протестировать FormattingHelperService, а вместе с ним тестируем UpperCaseService.
  // Представте, что если у вас длинная вложенность сервисов и что ????? Все их создавать ???
  "format" should "удалять пробелы и правильно вызывать сервис" in { // тут format, это название метода, которые мы тестируем
    val upperCaseService = new UpperCaseService()
    val formattingHelperService = new FormattingHelperService(upperCaseService)

    val in = " Hello world! "
    val out = "HELLO WORLD!"

    val result = formattingHelperService.format(in)
    result shouldBe out
  }

  it should "удалять пробелы и правильно вызывать сервис с использованием мока" in { // если мы тестируем один метод дважды, а именно два теста на format, то мы пишем it. Оно подставит название с первого теста автоматически.
    val upperCaseServiceMock = mock[UpperCaseService] // никаких new тут нет, мок это просто заглушка - фейк.
    val formattingHelperService = new FormattingHelperService(upperCaseServiceMock)

    val in = " Hello world! "
    val out = "HELLO WORLD!"

    // Перед тем как вызвать тестируемый метод, мы должны научить заглушку как вести себя как будто она настоящий класс.
    // Если вызвать тест без этих подсказок, то мы получим ошибку.

    (upperCaseServiceMock.up _).expects("Hello world!").returns("HELLO WORLD!") // говорим, что для заглушки up при вызове её с параметром expects мы вернем соответсвующий результат

    val result = formattingHelperService.format(in)
    result shouldBe out
  }

}

class UpperCaseService {
  def up(str: String): String = str.toUpperCase()
}

class FormattingHelperService(upperCaseService: UpperCaseService) {

  def format(str: String): String = {
    val strWithoutSpaces = str.trim // trim удаляем пробел в начале и конце строки
    upperCaseService.up(strWithoutSpaces) // вызываем зависимый сервис
  }
}