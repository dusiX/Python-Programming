def order(sentence):
    list = sentence.split(" ")
    i = 1
    j = -1
    l = 0
    length = len(list)
    final = ""
    if len(sentence) != 0:
        while i != (len(list) + 1):
            j += 1
            for k in range(len(list[j])):
                if list[j].find(str(i)) != -1:
                    i += 1
                    if i == (len(list) + 1):
                        final += list[j]
                    else:
                        final += list[j] + " "
                    j = -1
    return final
