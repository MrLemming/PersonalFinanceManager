# Менеджер личных финансов


Перед Вами серверное Maven-приложение, играющее роль менеджера личных финансов.
После получения и обработки сервером информации о покупке, клиенту высылается статистика по категории с максимальными тратами.

В приложении предусмотрено автосохранение статистики в бинарном файле data.bin и unit-тестирование.

Сервер реализован в классе Main.
При запуске приложения восстанавливаются данные статистики из автосохранения (если файл data.bin существует),
либо создается новый объект статистики.
Через класс Client можно отправить данные о товаре, дате покупки и стоимости. Эти данные передаются на обработку объекту класса Statistic.

Данный класс Statistic реализует следующие методы:
* loadCategoriesFromTSV - метод формирования Map категорий из файла categories.tsv;
* getMaxCategory - метод расчета категории с максимальными тратами;
* addItem - метод добавления товара к категории и в случае его отсутствия в файле categories.tsv - присвоение товару категории "другое";
* saveBin - метод сохранения данных в файл data.bin;
* loadFromBinFile - метод загрузки данных из файла data.bin;
* getServerResponse - метод преобразует результаты расчетов в строку json и отправляет клиенту.

Класс Request описывает структуру запроса.

Класс ProductsCategory описывает структуру данных категории товаров. 