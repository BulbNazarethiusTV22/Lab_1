# Lab_1

Програма реалізує асинхронну симуляцію роботи парковки з динамічною кількістю місць, яка залежить від часу доби (5 місць удень і 8 уночі). Кожен автомобіль запускається у вигляді окремого потоку, що дозволяє йому діяти незалежно від інших. Семафори забезпечують синхронізацію потоків, обмежуючи доступ до паркомісць відповідно до поточного часу. Автомобілі прибувають на парковку з випадковими інтервалами, чекають на вільне місце, паркуються (затримка моделюється випадковим часом) і звільняють місце, не блокуючи роботу інших потоків. Уся взаємодія потоків відображається в реальному часі у вигляді повідомлень у консолі.
