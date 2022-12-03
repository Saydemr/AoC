import math

def elf_score(char):
    if char.isupper():
        return ord(char) - 38
    else:
        return ord(char) - 96


score = 0
with open('input3.txt', 'r') as f:
    for line in f:
        together = ""
        line = line.strip()
        fp = line[: math.ceil(len(line)/2)]
        sp = line[math.floor(len(line)/2):]

        
        for i in range(len(fp)):
            if fp[i] in sp:
                together += fp[i]
                break
        

        score += elf_score(together)

print(score)
