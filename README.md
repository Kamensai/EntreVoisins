# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**.

# Application "Entre Voisins"
***
Cette application a pour but de mettre en relation les personnes d'un même quartiers afin
qu'ils puissent sse rendre des petits services au besoin : garde d'animaux, bricolage etc...

## Les fonctionnalités de l'application
***
 * Lister les voisins
 * Ajouter un voisin
 * Supprimer un voisin
 * Afficher les détails d'un voisin
 * Ajouter un voisin dans les favoris

 ## Technologies utilisées pour le développement de l'application
 ***

 * L'application est développée sur Android Studio 4.1.1

 * CompileSdkVersion : 28
    minSdkVersion 21
    targetSdkVersion 28
 Il sera possible d'accéder à une version plus récente du SdkVersion(30) prochainement.

 *  Application testée sur les smartphones et tablettes sous la version des API ci-dessous:
    API 25
    API 26
    API 30

 * Dependencies :
     implementation fileTree(dir: 'libs', include: ['*.jar'])
     implementation 'androidx.appcompat:appcompat:1.0.0'
     implementation 'com.google.android.material:material:1.0.0'
     implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
     implementation 'androidx.legacy:legacy-support-v4:1.0.0'
     implementation 'androidx.recyclerview:recyclerview:1.0.0'
     implementation "androidx.cardview:cardview:1.0.0"
     implementation 'com.jakewharton:butterknife:10.0.0'
     annotationProcessor 'com.jakewharton:butterknife-compiler:10.0.0'

     implementation 'com.jakewharton:butterknife:9.0.0'
     implementation 'androidx.cardview:cardview:1.0.0'
     annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0'

     implementation 'com.github.bumptech.glide:glide:4.9.0'
     annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'

     // EVENT BUS
     implementation 'org.greenrobot:eventbus:3.1.1'

     // UNIT TEST
     testImplementation 'junit:junit:4.12'
     testImplementation 'org.hamcrest:java-hamcrest:2.0.0.0'

     // INSTRUMENTATION TEST
     androidTestImplementation 'androidx.test:rules:1.1.1'
     androidTestImplementation 'androidx.test.ext:junit:1.1.1'
     androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0'
     androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.1.0'
     androidTestImplementation 'androidx.test.espresso:espresso-intents:3.1.0'
     androidTestImplementation 'androidx.test:runner:1.1.0'
     androidTestImplementation "org.mockito:mockito-core:2.+"

  ## Etat du projet
  ***
  L'application répond actuellement à la demande des premiers utilisateurs.
  Il sera possible de l'améliorer suite aux futurs retours de ceux-ci.
