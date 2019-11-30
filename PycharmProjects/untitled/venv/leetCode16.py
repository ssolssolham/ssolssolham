num1 = [4,1,2]
num2 = [1,3,4,2]
answer = []

for it in num1:
    temp = it
    ord = 0
    target = 0

    for order,item in enumerate(num2):
        if item == temp:
            ord = order
            target = item
            break

    temp2 = -10
    for item in num2[ord + 1:]:
        if target < item and temp2 == -10:
            temp2 = item
            answer.append(item)

    if temp2 == -10:
        answer.append(-1)

print(answer)