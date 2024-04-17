# TI59 Emulator

ti5x is an emulator, running on Android, for the venerable
TI-58/58C/59 family of programmable scientific calculators
which were manufactured by Texas Instruments from the late
1970s.

ti5x does aim for 100% authenticity even if not possible
in all cases. Yet, it supports many undocumented features
from the original calculator like the HIR registers and
the /DSZ nn 51/ (decrement no-jump) instructions.

With the current version it is possible to play the famous
Treasure-Island and 3D-Tic-Tac-Toe games.

## Location of programs:

The saved programs where stored into the Programs directory at
the root of the file system. Since Android-10 it is not possible
anymore to access this location for security reasons.

The programs are now saved into

  Android/Data/net.obry.ti5x/files/Programs

You need to manually move the programs from Programs/*.ti5p to
Android/Data/net.obry.ti5x/files/Programs to get access to them
with version 7.9.

## Content:
    src/ -- Java sources for the Android app
    res/ -- resources for the Android app
    assets/ -- additional data (help file) for the Android app
    AndroidManifest.xml, build.xml, *.properties -- for driving
        Google's Android build tools (note that you will have to
        provide a couple more of these--see INSTALL for details)
    util/ -- Python scripts for assembling/disassembling
        calculator programs, building the Master Library and
        other useful tasks.
    modules/
        av/   -- the source of the Aviation Library.
        bd/   -- the source of the Business Decision Library.
        ce/   -- the source of the Civil Engineering Library.
        ct/   -- the source of the Contribution Library.
        ee/   -- the source of the Electrical Engineering Library.
        fm/   -- the source of the Agriculture Library.
        le/   -- the source of the Leisure Library.
        ml/   -- the source of the Master Library.
        mu/   -- the source of the Math Utilities Library.
        ng/   -- the source of the Marine Navigation Library.
        re/   -- the source of the Real Estate / Investment Library.
        rp/   -- the source of the RPN Simulator Library.
        sa/   -- the source of the Structural Engineering Library.
        st/   -- the source of the Applied Statistics Library.
        sy/   -- the source of the Surveying Library.
    programs/
        ee19_input_code/         -- builtin companion program for EE-19
        ee19_construct_nam_code/ -- builtin companion program for EE-19
        demo_card_store/         -- builtin demo of a card store
    art/      -- artwork for diagrams, higher-res scan for icon
    tests/    -- some unit tests
    examples/ -- some simple examples for building user's programs
    README.md -- this file
    INSTALL   -- build/installation instructions
    COPYING   -- license (GPLv3)

## MeWee

   Come and join the TI59 group on MeWee.

   https://mewe.com/join/ti59

## Maintainers

### Current

   Pascal Obry <pascal@obry.net>

### Original Author:

   Lawrence D'Oliveiro <ldo@geek-central.gen.nz>
