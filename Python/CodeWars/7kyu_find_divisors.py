def divisors(integer):
    x = []
    for num in range(2, integer):
        if integer%num == 0:
            if int(integer/num) > num:
                x.append(num)
                x.append(int(integer/num))
            elif int(integer/num) == num:
                x.append(num)
    if not x: 
        return str(integer) + ' is prime'
    else:
        return sorted(x)
