ipt = ["bella","label","roller"]
ipt = ["acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"]
ipt = ["acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"]
# ['b', 'e', 'l', 'l', 'a']
def chk_char(character,doc):
    if character in doc.keys():
        return doc[character]
    else:
        return 0

alphaSets = []

for item in ipt:
     lst = list(item)
     alphaSet = set()
     docu = {}

     for lstitem in lst:

         if chk_char(lstitem, docu) == 0:
             docu[lstitem] = 1
             alphaSet.add(lstitem)
         else:
             docu[lstitem] += 1
             alphaSet.add( lstitem + str(docu[lstitem]))
     alphaSets.append(alphaSet)

fulldocu = {}

for st in alphaSets:
   for item in st:
       if chk_char(item, fulldocu) == 0:
           fulldocu[item] = 1
       else:
           fulldocu[item] += 1

result = []
for item in fulldocu:
    if fulldocu[item] > 2:
        if item[1:] != '':
            result.append(item[0])
        else:
            result.append(item[0])

print(result)







