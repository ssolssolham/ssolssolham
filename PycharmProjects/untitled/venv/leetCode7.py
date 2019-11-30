#words = ["hello","world","leetcode"]
#chars = "welldonehoneyr"

words = ["cat","bt","hat","tree"]
chars = "atach"

charList = list(chars)
dic = {}
answer = 0

for ch in charList:
    if dic.get(ch) == None:
        dic[ch] = 1
    else:
        dic[ch] += 1


for voca in words:
    dic2 = {}
    for item in list(voca):
        if dic2.get(item) == None:
            dic2[item] = 1
        else:
            dic2[item] += 1

    tf = True
    for key,val in dic2.items():
        if dic.get(key) == None:
            tf = False
            break
        if dic.get(key) < val:
            tf = False
            break

    if tf:
        answer += len(voca)

print(answer)