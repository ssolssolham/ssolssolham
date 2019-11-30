ipt = ["bella","label","roller"]
#arr1 = [[2, 3, 2], [4, 2, 4], [3, 1, 4]]
#arr2 = [[5, 4, 3], [2, 4, 1], [3, 1, 1]]
#count = len(arr2[0])

answer = []
answer2 = []
dic = {}


for item in ipt:
    tempDic = {}
    for ch in list(item):
        if tempDic.get(ch) == None:
            tempDic[ch] = 1
        else:
            tempDic[ch] += 1

    for key, val in tempDic.items():
        if dic.get(key) == None:
            dic[key] = val
        if dic.get(key)  tempDic:
