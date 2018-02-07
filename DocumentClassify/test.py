f=open('stanford_mac.txt')
sentences = f.read()
word_meaning = sentences.split(None)
print(len(word_meaning))
count=0
for i in range(len(word_meaning)):
    for j in range(len(word_meaning[i])):
        if word_meaning[i][j] is '/':
            break
        else:
            count+=1
    if '/NN' in word_meaning[i] or '/VB' in word_meaning[i] or 'JJ' in word_meaning[i]:
        print(word_meaning[i][0:count],end=' ')
    count=0
