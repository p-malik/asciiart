package com.asciiart;

import com.asciiart.converter.CoordinateConverter;
import com.asciiart.model.XYCoordinate;
import com.asciiart.service.AsciiArtGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class AsciiTestIT {

    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setup() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void destroy() {
        outContent = null;
        System.setOut(null);
    }

    @Test
    public void asciiArtGenTest() throws Exception {

        XYCoordinate xyCoordinate = CoordinateConverter.convertCordinatesToXY("ukpostcodes.csv", "src/main/resources/ukpostcodes.csv.zip");

        AsciiArtGenerator service = new AsciiArtGenerator();
        service.generateMap(xyCoordinate);
        Assertions.assertEquals(expectedMap(), outContent.toString().replaceAll("\\s", ""));
    }

    private String expectedMap() {
        return new String("*********#***##**##*##*##*####****####***####****####*****#####**#***#####**#*#######****#**#######****#####**######****##*#*#######*****#*###**####******####**###********#########*********#########**********###########********#########********########******##########******############******############********############*********#**#########************########**********#**######**********#####*******#####********######****#***#####****#*#*####*****#***#*##*******##**#***#***##**##*******##**##*********#**##******#*****##**********#*##*****#******##**#**#*****##**#********##**#*#*****#************##***********##****##***##*****#******#*********#**#****#*#**#**#******#**##*#********##*#*************#*****#*******#***************#******#*********#******#*********#******************#***********************#*****#***#****************#**#********#*****##*************#**#*****#******###*************#**#*******#*****###***************#******##*****#*******************#*******#*#*#**#**************#*********#*#*#**#***************#*********####*******#**************#*******####*###****##**************#******##*##*##*****##***************#******######*##**#**************#******##########**#****************#*****##########*****************#*******#######*****************#******#########****************#******##########**#****************#*******#############********#********#*******##**##*###***************#******###*#####******#********#*******#*##**###*******#********#******#**##***###*******#*******#******#*###***###******#*******#*********#*****##*******#*******#******#**#*#***#******#********#******#*#******#*********#********#******##********#***************#******###*********************#*#********#########**#************#*#*********#####*#*#****************#*#*************########***##***************#*#*******##*******#############*###*##*******************#*#********#****#***#####*****##*########*##***********#******#*#*****#**##*****#***#*********###########***********#******#*#*****##**#*###******##*******#####*#######*******************#*#********##**#####**#****#*****#*****#*#######***#************###*******#*****###*#******#*******#*##**#**###***#************###********##*******##*********#**********#******###***#***********###********##*#***************#************#*#***#*******************###*******#*#*#*#***#***#******#***#***#****#****#******#************###******#**####**#********#******#**************#**###**#***#*#*********###*****#*#***#*####****************##*****#***#**#**#**###**#**##***********###*****###*****####****************#***********#**#*###***#****#************###********##*******######***######**#*****#**#######*******####***#**************#*****###********#*######***#####******#***#************#*****#*****#****#****#***#****#*****###****#################*******#**************##****#*****#*****#*********#***********#*#***####**##*#######*******#***************#*******#**##****#**#*****************#*#***#**###*###**##*#******#*************#******#####*******#**#******************#*#******###**#***##*******************#*****#**##******#**#******#**********#****##*#*######*#********#**********#*****##**#*******#**#*******#**********#*****##**##**#**#*******##***************##*********##********#***#*****************#******#*#***##***#*****##***********#****#*##**#***##*********#****#*******************#*******#*******#*******##**#***********#######***##*******#**#*#***********#*******#**********#*#****#********#*******##**#***###**###********#*****************#******#*****#**##******#**#****#***********###***#####********#****************#******#******####****#**#****#*************##****####********##***#**********#******#*****####******#***#*********####**********####**####***#****##***#**********#******#****####*#****#***********####*#**##*##******##############****##***#********#******#****##**#*##***#**#***#*##########***#*****##**######****##*****##*************#******#****##***#*****#*****#*#******####*******#********##########*****##***##*****#***********#****##**##******#*##*##******###***************#########********##***##********#**#***#****###*#******#**#*##**********##****************######**#********##***##***********#***#*****#****************#**#*****#*****######*********######**#*********##***##****#********#***#*****#*************#***#####************##*********########***#*****###*##***##***************#***#*****#***************#**###*####******#********#####**#**#****#########**####***********#####**#***#*****#*************#********#*#######*******#******########***#*****################################**#***#*****##*###**********####**#****#****##*********##*#***########****#*###########################*#######*****#*****#*****##******#**#***#****#*********#*########****########******#############################*#########***#******#****##********#####**##******#*****###******#*****########################################################***#*****##***********#####***#****####*****####*****###############################*#############################****#****####*********#****####***#*******####*************###################****##*##*#############################****#***###*********##****####*#**#*######********#########################*##****##################################*****#***####*************#*#######****#*****##*****##################################***#################################***#******##********#####*####################**#****#####################****#********#################################***#*****#*********#######***###*##*######################################******##*****#################################***#****###*******#****#####################**############*#######****#**#*#****###*######*#####################****#****####*******#*#######*########******##########****###########****#*####*##########*####################*****#****###*******#**##############*########****##*****###########*#**#*###**###############################**#***#*****#**#******##***########*##########*******##***##############*#**#####*#######*########################******#****#****##***#####******##########**************#######*###***#*####**#######**######################******#****#******#****##****####*########**************#######*#*#***##*###**#######**######################*****#****#*******#***#*#*****######****###*******####**#**#**#**######***#**###########################*****#***#**#***#********##**######**###********#*###**#*****#**######***#**##########################*******#***#******#*******#**#*#####****#********#****###**#*****#######*#*#***###########################**#***##**###***#***###*###*##**********###***#*###*##***#*#########**##**##########################***#**###***###**#*****###**#*##**************#***##*##***#*####**#**#*##**#########################*******###*****##**#******####**#*******#*********##*###**######**#**#*##**#########################******###******#*******####**#**#***####********#****##*###***##**##****#*##**#########################*******###****************##*****#*******##########*****#####**#*##**##**#*#*##*##########################*****#**###****##**********####***#*********##*****##*###**##****#**###**#########**###################****#**###***###************##**##***#********#**#####*####**#*#**##########**#*###**#*################*****#**###****##*******#******######***#**#*****#*******#####*#**#*#*#####**####*#**##**#*################*****#**###*****##**************###**#*******##**#**###**##*####***#***####**##*#**#**##**#**###############********###***********#******#*####***##**********####******#**###**********###*##****##**#**##################********###*********#***##***#*##**##**#*****#*######****#**#****#####*#*###*##****##***######*#############******#**###*********#******#**##**##***##**###********#**#***########*###*##****##***####################******#**###********#****#*#####***#####****#***##**#****#####*#*######***##***####################*****#**###*********#****#*#######***###***********#**#***##*###*#*#######*#*##*######################*****#**###*********#*****##*##**####**####*********#**#****#*#####*##*####*#*####*####################******#***###********######***#**#######***#*******#*##****#######*##############*#####################******#**###********####*##**##**#**#####*###****#####*****######*####################################*****#**###*********#****#######**#**##########****################*###################################**#**#**###************###***#***##*#*#########****#####################################################***#**#**###**********************####***#######*#***####*#*#*##########################################*****#**###***************###****#*******#*#*****#####*****#####****############################################****##**###************####******##*****####***####*#######*###################################################*****##**###***********###*****#**#******####*****#**######*########*#**########################################**#**#**###***##*****##****##**###*##**###*******##*#*#######**##***###########################################*****#**###***####*******#**#####****##*####***######*#*#######**##****##########################################****#**###****###*********#######**####**#*####*#####**#****######***#***###########################################****#***###***##**************##**#####**########***********#######***#**#*#########################################****##**###****************####**#*##########*******###**###########**#*#########################################**#**##**###******************#***###***############******######**###################################################****##**###********#*****#####****###**##########***#############################################################*******###*********#*#######*****#############****#***#######**################################################********###******####****#*##****####*########**##**####*#*##**###############################################*#******###********###******##*#####**#######**###*####*#**#**################################*##############*#**#***###**********##*#****#################**##########***#*#######***####################################**#**#***###***********###****####*###########***############**#########**###################################***#**#***###************##*****#####**###########*######*###################################################**#**#***###**************##***####**############*##########################################################**#*******###***********####*#*###############*###########################################*#############**********###**********#####*########################################################################******#**####***********############################***####################*##########################******#**####****##*****#####**####################****####################*##*########################*****#***###**##***#######*######################**####################*###########################*****#***###**#*********#######*#***################*#**###############*##################*#############*****#***###**#*****#**#**#**####*###################*###################################*#############*****#****##**#****#***#**#**############################################################*#############***#*##***##**#****#**##****###########################################################################**#**#***##*#*********#***#*#*#########################################################################*****#***##*#*#*************############################################################################*****#*#*##****###*#********#############################################################################**#**#*####**###*##******######*#*#*#######################################################################**#**######*##*###**********###################################################################################****######*######**#############*#############################################################################****##*###**#*#*#**######***#######*#***######################################################################**#**#**##*******#*******#######***##**##*#####################################################################**#**#**##*******##***#########*****##**########################################################################*****#**##****#*###******##**#**#############################################################################****#**##*****###********##*#**##**####*#####################################################################*******##************###*#########******##################################################################*****#*##************#*####*#######*##**##################################################################*****####**********####*########*######################################################################****####************####################################################################################****####************#*########*#####**####################################################################*****###*************###*#**##*####***####################################################################******##************#####***#*####*##*###################################################################*#******##*************#####********#**#####################################################################*##***#*##**************#**#####*******##*#####################################################################*##**##*##*****************##*#********#**#####################################################################*****#####*********************###*********#*#####################################################################*****#####*********#**********************#**#####################################################################**#*#*###************###*********************#**#####################################################################**#***####******#***########*********************###########*###########################################################*****#####*****##############*******#**************#**####**##*#############################################################***#####*****##################***#**************##**#####*################################################################****####****################**#*#**************###############################################*#############################*#**####******###################*##***********###**#########################################################################*#***##*#*********#*######################***##*##***#*####**####*#######################################################################**##*********############################********#*##########***#######################################################################*##*****###############################*******########*###******#####################################################################*##*******##############################********#####**##***###**#####################################################################**##**#***##############################******#####***####**##**#####################################################################*###******##############################*******####****####**########################################################################*####*******####################################**####****######*#######################################################################*#####**#***##################################***######***####################################################################################**#******###################################*##*###***###################################################################################**#*****#######################################*##******###################################################################################**##***#######################################*##****#***##################################################################################**#**#########################################*#**##*#*###*****##########################################################################**#*******####################################################################################################################################***#******#################################################*#****#################################################################################**#***##################################################******####**###*########################################################################******######################################################**####****#*##**##################################################################**********#################################################**#######****##########################################################################**********###########################################################################*#############################################################********#############################################################################*##############################################################******######################################################################*#######*#################################################################*****####################################################################*###**#########################################################################***########################################################################*#######*####################################################################***###########################################################################**####**###################################################################**#####################################################################**#########**###################################################################****###########################################################################################*###############################################################################################################################*###**#########################################################################***#*########################################################################**######################################################################***###########################################################################*#############*########################################################***#################################################################################################################################################****###############################################################################################################################################**##############################################################################################################################################***###############*##############################################################################################################################*#############*****##########################################################################################################################**#########**####**##########################################################################################################################***##**#####****#############################################################################################################################***********#**####*########################################################################################################################*****###*#########################################################################################################################******##*#####**#################################################################################################################*******#####***######*#########################################################################################################********####***#*###***#######################################################################################################*********####*###*###**###*##################################################################################################**********######**##########################################################################################################*********######*##########################################################################################################********##**####**######################################################################################################*******#**######*#####################################################################################################********#*##*#########################################################################################################**********##**########################################################################################################****#*****##****#####################################################################################################***********##**#####**###############################################################################################***********##***#####################################################################################################*************##***###*####**##########################################################################################**********#**#****#**####*##########################################################################################******************#*#####*#*#######################################################################################******************##**####**######################################################################################*****************##***#######################################*##################################################*********#********#*#**##########################################################################################*#*******#*******###***#########################################################################################**********#*******###***##**####################################################################################*****************####****##**###################################################################################**********#********#***###***#################################################################################****************#**#***####**################################################################################**************#***#**#####*################################################################################****************#*****######################################################################################**#************#***#**#####################################################################################***********************####################################################################################************#**###***##**###################################################################################*#********#******#***##**#**###############################################################################*********************#**##*###############################################################################***********#**#***#**#################################################################################***************#****#*#################################################################################****#*********#***##################################################################################************##**##################################################################################************#******##################################################################################**********#**#****#################################################################################***************#*****#################################################################################************#*#*******################################################################################***************##******################*###############################################################********#**#**#****#################################################################################***************#**###*###############*##############################################################************#**#***######*###########################################################################**************#****#*#*############################################################################*************##**###*############################################################################*********#*****#******#*############################################################################*******************#*############################################################################**************#**#***#*###########################################################################**********#*****#****############################################################################**************#*###**############################################################################**********#**#*###***######*####################################################################*************#####**##**##*####################################################################***********#**#*###*####**######################################################################**************#######***######################################################################************####*#*###*########################################################################************###*#*###*########################################################################**************#***###*#*######################################################################************#***#####*######################################################################************###***#####*######################################################################*********#*###**###*########################################################################**********###*##***########################################################################***********###*##*##########################################################################*******###*###############################################################################********###*###############################################################################*******###################################################################################*********###################################################################################********###################################################################################******#**#####################################################################################******########################################################################################****#########################################################################################****###########################################################################################****############################################################################################****#############################################################################################******#########################################################################################*****#########################################################################################***########################################################################################*****##########################################################################################*****###########################################################################################*******#*########***####*########################################################################*****#########################################################################################**##########**#**########################################################################***#***##*##**#**########################################################################******###*##**#**########################################################################*****###*##****########**###############################################################*****#***#****##*#####**#*#############################################################******#***#**##**######***#############################################################***#**###*#***#***#####*#**#############################################################******###*#**##***####**#*##############################################################******####*#***##**######**###############################################################*****#*####**##***#####****##############################################################*****######*###**######*#**##############################################################");
    }

}