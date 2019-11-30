arr = [4,2,5,7]
arrEven = []
arrOdd = []
result = []

for num,item in enumerate(arr):
    if item % 2 == 0:
        arrEven.append(item)
    else:
        arrOdd.append(item)


i = 0
length = len(arrEven) + len(arrOdd)
while i != length:
    if i % 2 == 0:
        result.append(arrEven.pop())
        i += 1
    else:
        result.append(arrOdd.pop())
        i += 1

print(result)