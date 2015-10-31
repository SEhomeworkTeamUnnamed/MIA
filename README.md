# MIA
Mathematica input assistant(for starters)


This is a mathematica input assistant for starters and people who infrequently use it.



Tips for team members:

--by jwt 2015-10-31

You should know a little bit about what is Git and what is GitHub.
Use www.baidu.com if you feel that you are not clear enough about them.
<br>
>1. Set up the project on your own computer:
--------------------------------------------

####1.1 Get your own GitHub account

####1.2 Download GitHub Desktop (https://desktop.github.com/) and install
        a brief introduction to it: [clickhere](https://github.com/integrations/github-desktop)
        
        it will install a GitHub and a Git Shell
        GitHub is mainly used to visualize change history
        Git Shell is for version control and changes submit
####1.3 Accept invitation to the SE2015homeworkGroup and the Unnamed team
        you should offer me your GitHub account name for me to invite you
####1.4 Clone MIA to your Git Desktop 
    
        by clicking "Clone in Desktop" button at the lower right corner on page 
            https://github.com/SEhomeworkTeamUnnamed/MIA
        or by clicking "+" button at the upper left corner on GitHub Desktop, then
            choose "SEhomeworkTeamUnnamed"(it should appear if you accept the invitation successfully), "clone", "MIA" and finally "Clone repository"
            
        in both way GitHub Desktop will ask you to browse a path for you to put the clone of MIA.
            default path(C:\Users\<your account>\Documents\GitHub) is suggested
            
####1.5 Create your own branch by GitHub Desktop(find the button yourself) and publish it(find the button yourself)
    
    <br>
>2. Open the project in IntelliJ IDEA(IDEA for brief):
-----------------------------------------------------

####2.1 open IDEA and create a new java project at the path where you put MIA
    
        it will recognize MIA as a project with Git, and asking you to fix the problem that it can't find git.exe
            to solve this: 
                click "fix it" and browse to 
                    C:\Users\<your account>\AppData\Local\GitHub\PortableGit_<this part might vary>\bin\git.exe
                so that the path of git.exe is known by IDEA. There is also a "test" button, click which will tell you that git.exe works.
        
####2.2 start reading code and writing code!
    <br>
>3. Using Git Shell
------------------

    Although GitHub Desktop offers functions such as publish and synchronize, they are not clear enough. 
    Sometimes you modified your file and clicked "sync" in GitHub Desktop, but the file remains unchanged on the GitHub website. So we need Git Shell.
    
    Introduction to some Git commands:
    
        [clickhere](http://blog.csdn.net/chun799/article/details/9095635)
        
    We seem only need commands I listed below:
    
####3.1 Browse to the directory of project MIA
        if you clone MIA in the suggested path, you only need to input "cd MIA"
        
####3.2 Use 
    
                git status
                
        to check modified files, files not change and files Git didn't track
        There are usually three kinds of file status:
            Changes to be committed: files whose changes will be uploaded
            Changes not staged for commit: files changed but won't be uploaded
            Untracked files: files which Git didn't track their changes
            
####3.3 Use 
    
                git add \<foldername or filename>
                
        to add folders and files from "Untracked files" and "Changes not staged for commit"
            to "Changes to be committed"
            
####3.4 Use 
    
                git commit -m "\<some notes>"
                
        to commit changes of files which are in "Changes to be committed"
        Be aware that after this command the changes is still local
        
####3.5 Use
    
                git push
                
        to submit your local changes online
        things on [MIA](https://github.com/SEhomeworkTeamUnnamed/MIA) should change now
        
####3.6 pull request
        (will be used in the future)
        
        <br>
I hope you can go through all this and on Monday we will solve problems you meet during these and discuss something else.
    
