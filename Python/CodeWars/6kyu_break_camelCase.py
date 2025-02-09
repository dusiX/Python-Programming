def solution(s):
    final = s
    for i in range(len(s)):
        if s[i].isupper():
            final = final[:i + (len(final)-len(s))] + " " + s[i:]
    return final
