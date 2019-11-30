ipt = [1,1,1,1,1,1,1,1]
answer = 0

ipt.append(-1)

for order,item in enumerate(ipt):
    if item != -1 :
        if item > ipt[order+1] and ipt[order+1] != -1:
            print(order)
            answer += 1

print(answer)



