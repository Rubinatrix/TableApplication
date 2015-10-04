# TableApplication
Very simple excel functions, MVC pattern, Exceptions

Маленький Эксель
----------------

Необходимо реализовать простую электронную таблицу в виде программы, выполняющейся
из командной строки. Она должна уметь обрабатывать ячейки таблицы как и более
продвинутые аналоги, только с упрощенным синтаксисом выражений. Каждая ячейка
может содержать:
 - Ничего
 - Неотрицательное целое число
 - Текстовые строки, которые начинаются с символа '
 - Строки-выражения, которые начинаются с символа '=' и могут содержать
   неотрицательные целые числа, ссылки на ячейки и простые арифметические
   выражения. Скобки запрещены, у всех операций одинаковый приоритет.
   Ссылки на ячейки состоят из одной латинской буквы и следующей за ней
   цифры.

   Эти ограничения введены для упрощения разбора выражений, поскольку разбор
   выражений не является основной частью проблемы. Вы можете спокойно
   положиться на эти ограничения. Вот грамматика содержимого ячейки:

      expression ::= '=' term {operation term}*
      term ::= cell_reference | nonnegative_number
      cell_reference ::= [A-Za-z][0-9] -- 
      operation ::= '+' | '-' | '*' | '/'
      text ::= '\'' {printable_character}

Процесс обработки:
 - Все выражения должны быть заменены на вычисленный результат.
 - Все вычисления выполняются с помощью целочисленной арифметики со знаком.
 - Ячейки с текстом должны быть вычислены как соответствующий текст без
   префикса '.
 - Операции над строками текста запрещены.
 - В случае любой ошибки вычисления формулы, вычисляемая ячейка должна содержать
   слово-сообщение об ошибке, начинающееся с символа '#'. Используйте короткие,
   ясные сообщения. Не надо предоставлять подробности об ошибках в выводе.

Программа должна использовать только стандартные библиотеки и классы и не должна
вызывать сторонние программы, библиотеки или системные компоненты.


Ввод и вывод
------------

Программа получает описание таблицы с формулами из стандартного ввода,
вычисляет ее и печатает полученный результат в стандартный вывод. Входные
данные представлены таблицей, элементы строк которой разделены табуляциями.
Первая строка содержит пару чисел, разделенных табуляцией - высоту и
ширину таблицы, соответственно. Затем идут строки с ячейками таблицы,
в грамматике, приведенной выше.


Выход должен содержать только ожидаемую информацию, включая сообщения об
ошибках, и никакой другой информации в выводе не должно быть, включая и
welcome text. Выход должен быть отформатирован в соответствии с приведенным
ниже примером.

Пример данных:
3            4
12          =C2       3       'Sample
=A1+B1*C1/5 =A2*B1    =B3-C3  'Spread
'Test       =4-3      5       'Sheet

Ожидаемый вывод:
12      -4      3       Sample
4       -16     -4      Spread
Test    1       5       Sheet


Указания по решению
-------------------
Необходимо промышленное качество кода. Более короткое и читаемое решение
предпочтительней. Решение должно содержать тестовые примеры и код, использованные
в процессе создания решения. Не забудьте откомментировать код в ключевых
местах. Код должен быть устойчив к ошибкам.

Представьте, что это требования к первой фазе проекта. Необходимо реализовать
только то, что нужно на этой фазе. Однако, известно, что планируется вторая
фаза, в которой требования будут расширены.
