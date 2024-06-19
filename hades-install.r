options(install.packages.compile.from.source = "never")

# edit the .Renviron file
install.packages("usethis")
library(usethis)
edit_r_environ()

# install ETL-Synthea
library(remotes)
install_github("OHDSI/ETL-Synthea@v2.0.0", upgrade = "always")

# do the installation
.libPaths()
install.packages("remotes")
library(remotes)
install_github("OHDSI/Hades@a0b5d4050e33e469c27d3fdd5677b0f607a8425d", upgrade = "always")

# confirm results
.libPaths()
installed.packages()


