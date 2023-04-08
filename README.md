# ylab_study

Репозиторий со всем сделанными домашками к курсу по Java.

Решения в директории: <b>src/main/java/io/ylab/intesive/</b>

Тесты: <b>src/test/java/</b>


# Домашка #1
> 1. [Stars](src/main/java/io/ylab/intensive/task_lecture1/Stars.java)
> 2. [Pell numbers](src/main/java/io/ylab/intensive/task_lecture1/Pell.java)
> 3. [Multiplication Table](src/main/java/io/ylab/intensive/task_lecture1/MultTable.java)
> 4. [Guess](src/main/java/io/ylab/intensive/task_lecture1/Guess.java)

p.s. Тут подробно описывать каждую не буду - они простые.

# Домашка #2
> 1. [Sequences](src/main/java/io/ylab/intensive/task_lecture2/sequences)

Даны 10 последовательностей A - J. Необходмо найти закономерности и реализовать соответствующий интерфейс.

> 2. [Complex Numbers](src/main/java/io/ylab/intensive/task_lecture2/complex_numbers)

Реализовать класс, описывающий комплексное число со следующими операциями:
- создание числа по действительной и мнимой части или только по действительной;
- сложение;
- вычитание;
- умножение;
- операция вычисления модуля;

> 3. [RateLimitedPrinter](src/main/java/io/ylab/intensive/task_lecture2/rate_limited_printer)

Реализовать класс RateLimiterPrinter: класс имеет конструктор, в который передается
interval и метод print(String). Класс функционирует по следующему принципу:
на объекте класса вызывается метод print(). Далее идет проверка, когда был последний вывод в консоль.
Если интервал (в миллисекундах) между последним состоявшимся выводом и текущим выводом больше значения
interval, переданного в конструктор - то происходит вывод значения. Иначе - не
происходит, и сообщение отбрасывается. То есть класс ограничивает частоту вывода в
консоль.

> 4. [Snils Validator](src/main/java/io/ylab/intensive/task_lecture2/snils_validator)

Номер СНИЛС состоит из 11 цифра, и валидация номера СНИЛС выполняется по [правилам](http://www.kholenkov.ru/data-validation/snils/).
Реализовать соответствующий интерфейс.

> 5. [StatsAccumulator](src/main/java/io/ylab/intensive/task_lecture2/stats_accumulator)

Реализовать интерфейс StatsAccumulator. Объект данного класса, будучи созданным, может принимать значения через метод
add. Приняв значение, объект меняет свое внутреннее состояние, чтобы в любой
момент времени предоставить данные о количестве переданных ему элементах,
минимальному (максимальному) из них, а также о среднем арифметическом всех переданных ему элементов

# Домашка #3

> 1. [Transliterator](src/main/java/io/ylab/intensive/task_lecture3/transliterator)

В соответствии с [правилами](https://www.gosuslugi.ru/help/faq/foreign_passport/100359) транслитерации реализовать интерфейс
Transliterator. Метод transliterate должен выполнять транслитерацию входной строки в выходную, то
есть заменять каждый символ кириллицы на соответствующую группу символов
латиницы. Каждый символ кириллицы, имеющийся во входной строке входит в нее в
верхнем регистре.

> 2. [DatedMap](src/main/java/io/ylab/intensive/task_lecture3/dated_map)

DatedMap - это структура данных, очень похожая на Map, но содержащая
дополнительную информацию: время добавления каждого ключа. При этом время
хранится только для тех ключей, которые присутствуют в Map.

Методы:
- <b>put</b>. Помещает в map пару ключ и значение. Как видно из описания метода, ключ и значение - это строки (семантика Map#put)
- <b>get</b>. Возвращает значение, связанное с переданным в метод ключом. Если для переданного ключа значение отсутствует - возвращается null (семантика Map#get)
- <b>containsKey</b>. Метод, проверяющий, есть ли в map значение для данного ключа (семантика Map#containsKey).
- <b>remove</b>. Получая на вход ключ, удаляет из map ключ и значение, с ним связанное (семантика Map#remove)
- <b>keySet</b>. Возвращает множество ключей, присутствующее в map (семантика Map#keySet)
- <b>getKeyLastInsertionDate</b>. Получая на вход ключ, проверяет, что для данного ключа существует значение в map. Если существует - возвращает дату, когда оно было
  добавлено. Если нет - возвращает nul.

> 3. [Org Structure](src/main/java/io/ylab/intensive/task_lecture3/org_structure)

Структура организации записана в виде строк в CSV файле. Каждая строка представляет собой одну запись
(объект). Поля объекта разделены специальным символом ;. Первая строка файла
содержит поля имена полей, все дальнейшие сроки содержат непосредственно
данные. В файле поле id обозначает уникальный идентификатор сотрудника, boss_id
идентификатор начальника, name - имя сотрудника, position - должность. Таким
образом, данные в файле описывают древовидную иерархию сотрудников. Необходимо написать программу, получает на вход CSV файл формата, описанного
выше и формирует структуру объектов класса

> 4. [Password Validator](src/main/java/io/ylab/intensive/task_lecture3/password_validator)

- Создать статический метод, который принимает на вход три параметра: login,
  password и confirmPassword.
- Login должен содержать только латинские буквы, цифры и знак подчеркивания.
  Если login не соответствует - выбросить WrongLoginException с текстом “Логин
  содержит недопустимые символы”
- Длина login должна быть меньше 20 символов. Если login не соответствует этим
  требованиям, необходимо выбросить WrongLoginException с текстом “Логин
  слишком длинный”
- Password должен содержать только латинские буквы, цифры и знак
  подчеркивания. Если password не соответствует этим требованиям, необходимо
  выбросить WrongPasswordException с текстом “Пароль содержит недопустимые
  символы”
- Длина password должна быть меньше 20 символов. Если password не
  соответствует этим требованиям, необходимо выбросить
  WrongPasswordException с текстом “Пароль слишком длинный”
- Также password и confirmPassword должны быть равны. Если password не
  соответствует этим требованиям, необходимо выбросить
  WrongPasswordException с текстом “Пароль и подтверждение не совпадают”
- WrongPasswordException и WrongLoginException - пользовательские классы
  исключения с двумя конструкторами – один по умолчанию, второй принимает
  сообщение исключения и передает его в конструктор класса Exception.
- Обработка исключений проводится внутри метода. Обработка исключений -
  вывод сообщения об ошибке консоль
- Метод возвращает true, если значения верны или false в другом случае.


> 5. [File Sort](src/main/java/io/ylab/intensive/task_lecture3/password_validator)

Класс Generator, которые генерирует файл с заданными количеством чисел типа long.
Класс Validator, который проверяет, что файл отсортирован по возрастанию.
Класс Sorter получает на вход файл с числами и возвращает
отсортированный по возрастанию файл.
Класс Test, который запускает генерацию файла, затем сортировку и проверку, что
файл отсортирован.

Задача - реализовать метод Sorter.sortFile используя алгоритм внешней
сортировки слиянием.

Для проверки рекомендуется сгенерировать файл из 375_000_000 записей, тогда объем
файла, который надо будет отсортировать, будет равен 7-8 Гб.

# Домашка #4

> 1. [Movie Database](src/main/java/io/ylab/intensive/task_lecture4/movie)

Скачать [файл](https://perso.telecom-paristech.fr/eagan/class/igr204/data/film.csv), в котором содержатся данные о фильмах.
Необходимо написать код, читающий данные из файла и записывающий в таблицу через
JDBC. Для добавления данных использовать PreparedStatement.
В работе реализовать класс Movie.

Данные, считываемые из файла должны быть упакованы в экземпляр
указанного класса. Затем этот экземпляр должен передаваться коду, который
будет отвечать за сохранение данных в БД.

Обратить внимание, что в файле некоторые значения могут
отсутствовать.

> 2. [Persistent Map](src/main/java/io/ylab/intensive/task_lecture4/persistentmap)

Необходимо реализовать Map, хранящий свое состояние исключительно в базе
данных. То есть, любое изменение данных Map (добавление и удаление), а также
получение данных должно транслироваться в соответствующие SQL запросы. Данные
необходимо хранить в таблице следующего вида.

- <b>name</b> - имя экземпляра Map;
- <b>key</b> - ключ в экземпляре Map;
- <b>value</b> - значение, соответствующее ключу в текущем экземпляре Map.

Реализовать соответствующий интерфейс PersistentMap:
- <b>init</b>. Метод используется для инициализации нового экземпляра Map. Принимает имя
  текущего экземпляра. Данные всех экземпляров хранятся в одной таблице, и имя
  используется для того, чтобы отделять данные одного экземпляра от данных другого;
- <b>containsKey</b>. Возвращает true тогда и только тогда, когда существует значение,
  связанное с данным ключом, false - в противном случае;
- <b>getKeys</b>. Возвращает список ключей, для которых есть значения в БД;
- <b>get</b>. Возвращает значение, связанное с переданным ключом;
- <b>remove</b>. Удаляет пару ключ/значение из Map;
- <b>put</b>. Служит для добавления новой пары ключ-значение. В своей работе сначала
  удаляет существую пару из Map (если она есть), а затем добавляет новую;
- <b>clear</b>. Удаляет все данные из текущего экземпляра Map;
- <b>Допущение</b>: можно считать, что одновременно только одно приложение будет
  работать с конкретным экземпляром.

Создание таблицы производится отдельно.

> 3. [File Sort returns!](src/main/java/io/ylab/intensive/task_lecture4/filesort)

Реализовать интерфейс FileSorter. Реализация интерфейса получает на вход файл, состоящий из чисел (long),
разделенных переносом строки и возвращает файл, в котором эти числа
отсортированы в порядке убывания.
- Можно считать, что максимальный размер файла - 1000000 чисел;
- Сортировку необходимо реализовать средствами БД;
- Работа с БД - средствами JDBC;
- При вставке данных обязательно использовать <b>batch-processing</b>. Разобраться
  что это такое, для чего используется и как реализовать.

> 4. [Event Sourcing](src/main/java/io/ylab/intensive/task_lecture4/eventsourcing)

Написать 2 приложения, реализующие функционал асинхронной записи данных в БД.
Приложение оперирует классом Person, содержащий данные о людях (класс Person).

Архитектура:

![image](https://user-images.githubusercontent.com/92880196/230715719-5d0fd129-5e51-4840-a338-526c1139c955.png)

<b>Приложение DataProcessor</b>:
Принимает из RabbitMQ сообщения о добавлении/удалении данных, затем выполняет
в БД соответствующие запросы.
Необходимо, чтобы сообщения, отправленные первыми, обрабатывались также
первыми (чтобы принцип FIFO не нарушался)

<b>Приложение API</b>: Приложение содержит реализацию интерфейса PersonApi:

- <b>deletePerson</b> генерирует сообщение-команду на удаление персоны с заданным
  id. Далее это сообщение должно быть обработано соответствующим запросом,
  выполняя удаление данных. Если данных для определенного personId не
  найдено - выводить в лог сообщение, что была попытка удаления, но при этом
  данные не найдены. Exception или другую ошибку не выдавать
- <b>savePerson</b> генерирует сообщение-команду на сохранение данных персоны.
  Обработчик должен проверить, существует ли в БД персона с переданным
  personId. Если существует - необходимо выполнить обновление данных
  (обновить три поля firstName, lastName, middleName). Если не существует -
  создать персону с переданным personId.
- <b>findPerson</b>. Генерирует запрос напрямую в БД и возвращает данные персоны,
  если персона для данного personId найдена, null в противном случае
- <b>findAll</b>. Генерирует запрос напрямую в БД и возвращает данные о всех
  сохраненных в базе персонах на данный момент.

# Домашка #5

> 1. [Event Sourcing](src/main/java/io/ylab/intensive/task_lecture5/eventsourcing)

Задача из предыдущего ДЗ. Должно быть реализована с разбиением на Spring
компоненты, а потом запуском как spring приложения.

> 2. [Query Extender](src/main/java/io/ylab/intensive/task_lecture5/sqlquerybuilder)

Делая запрос к метаданным БД, необходимо узнать, какие колонки есть в
конкретной таблице и, используя эти данные, написать корректный SQL запрос
к этой таблице. Работа с метаданными должна производится при помощи
объекта java.sql.DatabaseMetaData.

Написать Spring Component, реализующий интерфейс SQLQueryBuilder.

Метод queryForTable получает на вход имя таблицы и выполняет следующее
1. Проверяет, что данная таблица есть в БД
2. Если таблицы нет - метод возвращает null
3. Если таблица есть - получает список колонок
4. На основании списка колонок составляется строка запроса вида
   “SELECT <col1>, <col2>, <col3> FROM <tablename>”
5. Данная строка возвращается в качестве результата выполнения метода

Пример: SELECT id, first_name, last_name, middle_name FROM person

> 3. [Message Filter](src/main/java/io/ylab/intensive/task_lecture5/messagefilter)

Необходимо написать приложение, которые бы осуществляло цензуру присылаемых
сообщений
1. Приложение получает сообщение из очереди input.
2. Сообщение - это строка, некоторый текст.
3. Приложение проверяет каждое слово полученного текста на предмет.
   полного совпадения с одним из слов из списка нецензурных выражений (без учета регистра). Слово - это
   последовательность символов.
4. Если слово совпадает с каким-либо словом из списка - то все буквы в этом
   слове, кроме первой и последней заменяются на *.
5. Если слово не совпадает - оно остается без изменений.
6. Модифицированное таким образом предложение отправляется в очередь output.

Пример:
Получено из очереди input: Fuck you, уважаемый!
Отправлено в очередь output: F**k you, уважаемый!

<b>Особенности реализации</b>:
1. Очереди input и output - очереди RabbitMQ.
2. Недопустимые слова должны быть сохранены в файле построчно, в каждой
   строке одно слово.
3. Таблица со списком нецензурных слов должна создаваться при запуске
   приложения. Но только в том случае, если такой таблицы нет в БД. (Изучить и
   использовать для проверки существования метод Connection.getMetaData()).
4. Список нецензурных слов хранится в файле words.txt. При запуске
   приложения, переместить данные в БД. При каждом новом запуске необходимо
   очищать таблицу в БД и записывать данные из файла снова.
5. При обработке необходимо делать запрос в БД для проверки нахождения слова
   в списке.
6. Приложение должно быть написано с использованием Spring Framework.
7. Для отправки сообщений в очередь Input использовать RabbitMQ management console.





