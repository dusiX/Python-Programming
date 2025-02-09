def solution(text, ending):
    return False if text.find(ending, len(text)-len(ending)) == -1 else True
