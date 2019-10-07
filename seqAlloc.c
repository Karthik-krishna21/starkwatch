#include <stdio.h>
#define BLOCKS 10
void main()
{
	int f[50]={0},len ,req[50]={0},st[50] ,k=0, i ;char c[50][50] = "" ;
	char *s;
	int x;
	do{
	printf("Enter starting location ,length of file and c");
	scanf("%d%d %s",&st[k],&len,&s);
	int flag=0;
	for(i=st[k]-1;i<(st[k]-1+len);i++)
	{
		if(f[i]!=0)
			flag=1;
		
	}
	if(flag==0)
	{
			for(i=st[k];i<st[k]+len;i++)
				f[i]=1;
			*c[st[k]]=s;
		req[k]=1;

	}
	else{
		req[k]=0;
	}
	
	k++;
	printf("add another file? 0/1?");
	fflush(stdin);
	scanf("%d",&x);
	}while(x!=0);
	printf("StartAddress \t Status \n");
	for(i=0;i<k;i++)
	{
		if(req[i])
		{
			printf("%d \t",st[i]);
			if(req[i])
				printf("Allocated\n");
			else
				printf("Not Allocated\n");
		}
	}
	printf("Status of memory blocks\n");
	for(i=0;i<BLOCKS ;i++)
	{
		printf("%d \t ",i+1);
		if(f[i])
			printf("Occupied\n");
		else
			printf("Free\n");
	}
	printf("Blocks\t Contents\n");
	printf("7 \t %c \n",c[6][0]);
	printf("8 \t %c \n",c[7][0]);
	printf("2 \t %c \n",c[1][0]);

}
