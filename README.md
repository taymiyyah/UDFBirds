<h1 align="center">UDFBrids</h1></br>

<p align="center">
:UDFBrids: A lightweight UDF obserber .
</p>
</br>
<p align="center">
  <a href="https://github.com/Huthefa"><img alt="License" src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/></a>
  <a href="https://github.com/Huthefa"><img alt="License" src="https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white"/></a>
  <a href="mailto:hfararjeh98@gmail.com"><img alt="License" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>

 <br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  
</p> <br>


### Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```Gradle
allprojects {
    repositories {
        mavenCentral()
    }
}
```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation 'com.github.Huthefa:UDFBrids:1.0.0'
}

## Usage

###  Step 1

    fun myDataState () = performNetworkCallOperation {
        getDateApi().convertFromGDateTOH("8/2016")
    }

###  Step 2
private fun observerMyState(dataState: DataState<MyData>) {
    myDataState()
        .onLoading { showLoadingDialog() }
        .onError {
          //  hideLoadingDialog()
         //   showError(message)
        }
        .onSuccess {
         //   data?.let {
         //       handleResult(it)
         //   }
        }
        .onIdle {
        //    hideLoadingDialog() 
        }
}


# License
```xml
Copyright 2021 taymiyyah (Huthefa ALfararjeh)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
