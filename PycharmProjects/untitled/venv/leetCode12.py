arr = [4,1,2,1,2]
# arr = [2,2,1]

arr.sort()
tf = True

while tf:
    temp = arr[0:2]
    if len(temp) == 1:
        print(temp[0])
        tf = False

    if tf and temp[0] == temp[1]:
        arr.pop(0)
        arr.pop(0)
    else:
        print(temp[0])
        tf = False