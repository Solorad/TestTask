Реализация обработчика событий
Необходимо написать реализацию интерфейса EventHandler.  События поступают на обработку из разных, никак не синхронизованных между собой, потоков.  События должны быть обработаны в том порядке – в котором они поступили в EventHandler.
Необходимо реализовать механизм запуска и остановки обработчика.  В случае вызова метода EventHandler. stopHandler()  должен обработать все имеющиеся в очереди события и только после этого завершить работу. Так же необходимо минимизировать процессорные затраты во время простоя обработчика (когда нет новых событий, а старые уже обработаны). При реализации не использовать пакет java.util.concurrent.
