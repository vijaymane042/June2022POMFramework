pipeline{
    agent any
    stages{
        stage("build"){
            steps{
                echo("build project")
            }
            
        }
         stage("Deploy to DEV"){
            steps{
                echo("Deploy to DEV env")
            }
            
        }
         stage("Deploy to QA"){
            steps{
                echo("Deploy to QA env")
            }
            
        }
        stage("Run Regression Automation Test"){
            steps{
                echo("Run regression test cases")
            }
            
        }
        stage("Deploy to Stage"){
            steps{
                echo("Deploy to Stage env")
            }
            
        }
        stage("Run Sanity Automation Script "){
            steps{
                echo("Run sanity test cases")
            }
            
        }
        stage("Deploy to PROD"){
            steps{
                echo("Deploy to PROD env")
            }
            
        }
    }
}