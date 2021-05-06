mkdir releases;
mkdir releases/app;
cp -r data releases/app;
cp -r image releases/app;
cp target/*.jar releases/app;
zip -r out.zip releases/;
