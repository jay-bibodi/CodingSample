/* Author(s): Bibodi Jay N. Section 3
 *
 * This is a lab6.c the csc60mshell
 * This program serves as a skeleton for starting for lab 6.
 * Student is required to use this program to build a mini shell
 * using the specification as documented in direction.
 * Date: Fall 2016
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>

#define MAXLINE 80
#define MAXARGS 20
#define MAX_PATH_LENGTH 50
#define TRUE 1

/* function prototypes */
void process_input(int argc, char **argv);
int parseline(char *cmdline, char **argv);
void handle_redir(int count, char *argv[]); 

/* ----------------------------------------------------------------- */
/*                  The main program starts here                     */
/* ----------------------------------------------------------------- */
int main(void)
{
    char cmdline[MAXLINE];
    char *argv[MAXARGS];
    int argc;
    int status;
    pid_t pid;

    /* Loop forever to wait and process commands */
    while (TRUE) {
	/* Print your shell name: csc60mshell (m for mini shell) */
	printf("\ncsc60mshell> ");

	/* Read the command line */
	fgets(cmdline, MAXLINE, stdin);

	/* Call parseline to build argc/argv: their limits declared above */
	argc=parseline(cmdline,argv);
	printf("Argc = %i \n",argc);
	int i;
	for(i=0;i<argc;i++)
	{
		printf("Argv[%i] = %s \n",i,argv[i]);	
	}
	/* If user hits enter key without a command, continue to loop again at the beginning */
	/*  Hint: if argc is zero, no command declared */
	/*  Hint: look up for the keyword "continue" in C */
	if(argc==0)
	{
		continue;
	}	
	/* Handle build-in command: exit, pwd, or cd  */
	if((strcmp(argv[0],"exit")) == 0)
	{
		exit(EXIT_SUCCESS);
	}
	else if((strcmp(argv[0],"pwd")) == 0)
	{
		char buf[MAX_PATH_LENGTH];
		char *bufptr;
		bufptr = getcwd(buf,MAX_PATH_LENGTH);
		if(bufptr!=NULL)
		{
			printf("Current Working Directory: %s \n",bufptr);
		}
		continue;				
	}
	else if((strcmp(argv[0],"cd")) == 0)
	{
		char *dir;
		if(argc==1)
		{
			dir = getenv("HOME");
		}
		else
		{
			dir = argv[1];
		}
		status = chdir(dir);	
		if(status == -1)
		{
			printf("Error Changing Directory \n");
		}
		continue;	
	}
 
	/* Else, fork off a process */
	pid = fork();
      switch(pid)
	{
	    case -1:
		perror("Shell Program fork error");
	        exit(1);
	    case 0:
		/* I am child process. I will execute the command, call: execvp */
		process_input(argc, argv);
		break;
	    default:
		/* I am parent process */
		if (wait(&status) == -1)
		    perror("Shell Program error");
		else
		    printf("Child returned status: %d\n",status);
		break;
	} /* end of the switch */
    } /* end of the while */
} /* end of main */
/* ----------------------------------------------------------------- */
/*                  parseline                                        */
/* ----------------------------------------------------------------- */
/* parse input line into argc/argv format */

int parseline(char *cmdline, char **argv)
{
    int count = 0;
    char *separator = " \n\t";
 
    argv[count] = strtok(cmdline, separator);
    while ((argv[count] != NULL) && (count+1 < MAXARGS)) {
	argv[++count] = strtok((char *) 0, separator);
    }		
    return count;
}
/* ----------------------------------------------------------------- */
/*                  process_input                                    */
/* ----------------------------------------------------------------- */
void process_input(int argc, char **argv) 
{
    /* Step 1: Call handle_redir to deal with operators:            */
    /* < , or  >, or both   
	*/
	handle_redir(argc,argv);
    /* Step 2: perform system call execvp to execute command        */
    /* Hint: Please be sure to review execvp.c sample program       */
    if (execvp(argv[0],argv) == -1) 
	{                                       			// change this line 
		perror("Shell Program");                                   
		_exit(EXIT_FAILURE);                                                 
    }                                                           
 
}
/* ----------------------------------------------------------------- */
void handle_redir(int count, char *argv[])
{
	int i=0;
	int output_redir=0;
	int input_redir=0;
	int fd=0;

	for(i=0;i<count;i++)
	{
		if(strcmp(argv[i],">") == 0)
		{
			if(i == 0)   // if i == 0 , print error else go below
			{
				fprintf(stderr, "No command found before redirect symbol\n");
				_exit(EXIT_FAILURE);
			}
			else
			{
				if(output_redir == 0)
				{
					output_redir = i;
				}
				else
				{
					fprintf(stderr, "Trying to open more than one file per redirection!\n");
					_exit(EXIT_FAILURE);
				}
			}
		}
		else if(strcmp(argv[i],"<") == 0)
		{
			if(i == 0)
			{
				fprintf(stderr, "No command found before redirect symbol\n");
				_exit(EXIT_FAILURE);
			}
			else
			{
				if(input_redir == 0)
				{
					input_redir = i;
				}
				else
				{
					fprintf(stderr, "Trying to open more than one file per redirection!\n");
					_exit(EXIT_FAILURE);
				}
			}
		}
	}
	
	if(output_redir != 0)
	{
		fd = open(argv[output_redir+1],O_CREAT|O_WRONLY|O_TRUNC,S_IRUSR|S_IWUSR);
		if(fd != -1)
		{
			dup2(fd,1);
			close(fd);
			argv[output_redir]= NULL;
		}
		else
		{
			fprintf(stderr, "Cannot write into file specified!\n");
			_exit(EXIT_FAILURE);
		}	
	} 
	
	if(input_redir != 0)
	{
		fd = open(argv[input_redir+1],O_RDONLY);
		if(fd != -1)
		{
			dup2(fd,0);
			close(fd);
			argv[input_redir]= NULL;
		}
		else
		{
			fprintf(stderr, "Cannot read from file specified!\n");
			_exit(EXIT_FAILURE);
		}	
	}
}