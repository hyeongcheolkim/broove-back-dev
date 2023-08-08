// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "../contracts-protocol/KIP/token/KIP17/KIP17.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Enumerable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Burnable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17MetadataMintable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Mintable.sol";
import "../contracts-protocol/KIP/token/KIP17/extensions/KIP17Pausable.sol";
import "../contracts-protocol/governance/Governor.sol";
import "./GrooveNft.sol";

contract GrooveNft2 is GrooveNft{
    constructor(string memory name, string memory symbol) KIP17(name, symbol) {}
}