#include<stdio.h>
#include<ctype.h>
int check(char);

int main(int argv,char *argc[]){
  FILE *fpin;
  FILE *fpout;
  char c;
  int count=0;
  if(argv != 3) {
    printf("need two file\n");
    return -1;
  }
  fpin = fopen(argc[1],"r");
  fpout =fopen(argc[2],"w");

  if(fpin == NULL){
    printf("don't open\n");
    return -1;
  }

  if(fpout == NULL){
    printf("don't open\n");
    return -1;
  }

  while((c = fgetc(fpin)) != EOF){
    c = (char)tolower((int)c);
    if(check(c) != 0){
      if(check(c)==2) count++;
      if(count == 20){
        fprintf(fpout,"\n");
        count = 0;
      }
      if(check(c) != 2) fprintf(fpout,"%c",c);
    }
  }

  fclose(fpin);
  fclose(fpout);

  return 0;
}

int check(char c){
  int n;
  if(('a' <= c &&  c <= 'z') || c == ' ') { n = 1;
  } else n = 0;

  if(c == '.') n = 2;
  return n;
}
