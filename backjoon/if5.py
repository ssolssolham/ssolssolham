A, B, C = map(int,input().split())
lst = [A,B,C]
for num1 in range(0,2):
    for num2 in range(1,3):
        if lst[num1] > lst[num2] :
            temp = lst[num1]
            lst[num1] = lst[num2]
            lst[num2] = temp
print(lst[1])