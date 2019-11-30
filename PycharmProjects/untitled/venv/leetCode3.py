#arr = [1,2,2,1,1,3]
arr = [1,2]
dic = {}

for i in arr:
    if dic.get(i) == None:
        dic[i] = 1
    else:
        dic[i] += 1

val = dic.values()
st = set(val)
if len(val) == len(st):
    return True
else:
    return False|

#for i in dic:

